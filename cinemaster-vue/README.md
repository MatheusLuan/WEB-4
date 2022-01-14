# cineapp
Esta é uma aplicação client-side (vue/javascript) usada para consumir web-services do sistema [cineclube](https://github.com/fscheidt/cineclube). As dependências desse projeto são:
- Vue.js
- Node.js
- Axios

## Setup do ambiente de desenvolvimento
Verifique antes de tudo se as seguintes bibliotecas estão instaladas no seu sistema operacional:

(1) **Instalar [Node.js](https://nodejs.org/en/)**

No linux ubuntu/debian executar os comandos abaixo para instalar o node:

```
curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -
```

```
sudo apt-get install -y nodejs
```

Testar se o node foi corretamente instalado:
```
node --version
```

Para outros sistemas operacionais ver: https://nodejs.org

(2) **Instalar o vue-client**
```
sudo npm install -g @vue/cli
```

---

## Project setup
Realizar as etapas a seguir apenas 1 vez para configurar o projeto na máquina local de desenvolvimento.

(1) **Clonar** o projeto cineapp
```
git clone https://github.com/fscheidt/cineapp.git
```

(2) Instalar módulos do vue
```
cd cineapp
npm install vue-router@4
npm install --save axios vue-axios
npm install bootstrap-vue bootstrap --save
npm install
```
O comando `npm install` vai instalar as bibliotecas de dependência do projeto. A pasta `node_modules` será criada dentro do projeto cineapp.

---

## Executa o servidor web
Por padrão usa a porta 8080
```
cd cineapp
npm run serve
```
Acessar pelo browser: http://localhost:8080/

Opcionalmente podemos alterar a porta do servidor para evitar conflito com o spring boot, por exemplo para a porta 3000:
```
npm run serve -- --port 3000
```

Acessar pelo browser: http://localhost:3000/