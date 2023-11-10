//import { drawStockBarChart } from "./graphic.jsx";
//import { SupplierForm } from "./components/form.jsx";

var overview_x = document.
getElementById('overview-x');

var tabSupplier = document.
getElementById('tab-supplier');

var tabSales = document.
getElementById('tab-sales');

var tabStock = document.
getElementById('tab-stock');

var tabEmployee = document.
getElementById('tab-employee');

function getCookie(name) {
    const cookieString = document.cookie;
    //console.log(cookieString);
    const cookies = cookieString.split('; ');

    for (const cookie of cookies) {
        const [cookieName, cookieValue] = cookie.split('=');
        if (cookieName === name) {
            return cookieValue;
        }
    }
}

const deletedSupplier = getCookie('removed');
if (deletedSupplier === 'true') {
    ReactDOM.render(<SupplierContent />, overview_x);
    document.cookie = 'removed=false; path=/restricted; max-age=3600';
} else {
    console.log('hola');
};

tabSupplier.addEventListener('click', () => {
    ReactDOM.render(<SupplierContent />, overview_x);
});

tabSales.addEventListener('click', () => {
    console.log('UnexpectedError... Sales')
});

tabStock.addEventListener('click', () => {
    google.charts.load('current', {'packages':['corechart','bar']});
    google.charts.setOnLoadCallback(drawStockBarChart);
});

tabEmployee.addEventListener('click', () => {
    ReactDOM.render(<EmployeeContent />, overview_x);
});