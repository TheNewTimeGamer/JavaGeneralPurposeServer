class Home {
    get(connection, request, document) {
        console.log("11!");
        let ip = connection.getIpAddress();
        let element = document.getElementById("test");
        element.html("Your ip: " + ip);
        return "200 OK";
    }

    testfunc() {
        return "WASD";
    }

}

const home = new Home();