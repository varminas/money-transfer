FROM node:12.2.0

RUN mkdir -p /app

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

COPY package*.json /app/

RUN npm ci
RUN npm install -g @angular/cli@7.3.9

COPY . /app/

EXPOSE 4200

CMD ng serve --host 0.0.0.0 --proxy-config proxy.conf.json
