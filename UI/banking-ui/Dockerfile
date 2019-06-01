# STEP 1 build static website
FROM node:12.3.1-alpine as builder

WORKDIR /app

COPY package*.json /app/

RUN npm set progress=false && npm ci

RUN npm install -g @angular/cli@7.3.9

# Copy project files into the docker image
COPY . /app/

RUN cd /app && npm run build --prod

# STAGE 2: Setup ###
FROM nginx:1.16.0-alpine

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From 'builder' copy website to default nginx public folder
COPY --from=builder /app/dist /usr/share/nginx/html

COPY ./nginx-custom.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
