console.log("launching server..")
const httpServer = server.createHttpServer(80);

httpServer.registerRoute("/", "home");

httpServer.open();

console.log("Server open!");