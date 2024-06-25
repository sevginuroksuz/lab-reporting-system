# Base image olarak node kullan
FROM node:14

# Uygulamanın çalışacağı dizini oluştur
WORKDIR /app

# package.json ve yarn.lock/package-lock.json dosyalarını image'e kopyala
COPY package*.json ./

# Bağımlılıkları yükle
RUN npm install

# Uygulama kodunu kopyala
COPY . .

# Uygulamayı build et
RUN npm run build

# Serve uygulamasını kullanarak build klasörünü serve et
RUN npm install -g serve
CMD ["serve", "-s", "build"]

# Uygulamanın çalışacağı portu belirt
EXPOSE 3000
