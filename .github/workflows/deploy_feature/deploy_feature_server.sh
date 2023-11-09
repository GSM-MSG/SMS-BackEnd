#!/bin/bash

profile_arn=$FEATURE_SERVER_PROFILE_ARN

# ami = ubuntu
instance_id=$(aws ec2 run-instances --image-id ami-086cae3329a3f7d75 --instance-type t2.micro --key-name sms-key --subnet-id subnet-0288374d5ee6ba4c8 --iam-instance-profile Arn="${profile_arn}" --security-group-ids sg-058ec5c5fee753faa --output text --query "Instances[0].InstanceId")

while [ "$(aws ec2 describe-instances --instance-ids "${instance_id}" --output text --query 'Reservations[*].Instances[*].[State.Name][0][0]')" != "running" ]
do
  echo "instance is not ready"
  echo "sleep for 5 second"
  sleep 5
done

sleep 5

install_command_id=$(aws ssm send-command \
    --instance-ids "${instance_id}" \
    --document-name "AWS-RunShellScript" \
    --comment "install Docker" \
    --cli-input-json file://installDocker.json  \
    --output text \
    --query "Command.CommandId")

while [ "$(aws ssm list-command-invocations \
    --command-id "${install_command_id}" \
    --details \
    --output text \
    --query "CommandInvocations[0].Status")" == "InProgress" ]
do
  echo "docker is being installed..."
  sleep 5
done

DnsName=$(aws ec2 describe-instances --instance-ids "${instance_id}" --output text --query 'Reservations[*].Instances[*].[PublicDnsName]')

scp -i "sms-key.pem" docker-compose-feature.yml ubuntu@"${DnsName}":~
scp -i "sms-key.pem" ./.env ubuntu@"${DnsName}":~

pull_command_id=$(aws ssm send-command \
    --instance-ids "${instance_id}" \
    --document-name "AWS-RunShellScript" \
    --comment "run Docker images" \
    --cli-input-json file://runImages.json  \
    --output text \
    --query "Command.CommandId")

#aws ec2 terminate-instances --instance-ids "${instance_id}"



