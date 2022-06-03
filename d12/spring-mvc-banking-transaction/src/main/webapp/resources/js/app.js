class App {
    static DOMAIN =location.origin;
    static BASE_URL_CUSTOMER=this.DOMAIN +"/api/customers";
    static BASE_URL_TRANSFER=this.DOMAIN +"/api/transfers";
    static BASE_URL_PROVINCE="https://vapi.vnappmob.com/api/province";

    static ERROR_400 = "Giao dịch không thành công, vui lòng kiểm tra dữ liệu.";
}
class Customer {
    constructor(id, fulLName, email, phone, locationRegion, balance) {
        this.id = id;
        this.fullName = fulLName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.balance = balance;
    }
}
class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId,wardName,address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Sender {
    constructor(id, fulLName, email, phone, locationRegion, balance) {
        this.id = id;
        this.fullName = fulLName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.balance = balance;
    }
}

class Recipient {
    constructor(id, fulLName, email, phone, locationRegion, balance) {
        this.id = id;
        this.fullName = fulLName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.balance = balance;
    }
}

class Deposit {
    constructor(customer, transactionAmount) {
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }
}

class Withdraw {
    constructor(customer, transactionAmount) {
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }
}

class Transfer {
    constructor(id, sender, recipient, transferAmount, fees, feesAmount, transactionAmount) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }
}
class TransferDTO {
    constructor(id, senderId,senderName, recipientId, recipientName, transferAmount, fees, feesAmount, transactionAmount) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }
}

class Transaction {
    constructor(id, customer, transfer) {
        this.id = id;
        this.customer = customer;
        this.transfer = transfer;
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