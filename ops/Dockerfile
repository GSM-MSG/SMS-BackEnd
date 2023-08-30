FROM nginx:alpine

COPY nginx-conf.txt /etc/nginx/conf.d/default.conf

EXPOSE 80

ENTRYPOINT ["nginx", "-g", "daemon off;"]
