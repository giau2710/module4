class App {
    static DOMAIN = location.origin;
    static BASE_URL_ADMIN = this.DOMAIN + "/api/admins";
    static BASE_URL_TOURS = this.DOMAIN + "/api/tours";
    // static BASE_URL_PROVINCE="https://vapi.vnappmob.com/api/province";

    static ERROR_400 = "Add tour fail";

}

class User {
    constructor(id, fullName, email, phone, username, password, role, deleted, activeStatus, avatar) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
        this.deleted = deleted;
        this.activeStatus = activeStatus;
        this.avatar = avatar;
    }
}

class Tour {
    constructor(id, name, startDay, endDay, departure, destination, price, details, deleted) {
        this.id = id;
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
        this.details = details;
        this.deleted = deleted;
    }
}

class TourDTO {
    constructor(id, name, startDay, endDay, departure, destination, price, details) {
        this.id = id;
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
        this.details = details;
    }
}


function showSuccess(msg) {
    Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: msg,
        showConfirmButton: false,
        timer: 1500
    });
}

function showError(msg) {
    Swal.fire({
        position: 'mid-center',
        icon: 'error',
        title: msg,
        showConfirmButton: false,
        timer: 1500
    });
}