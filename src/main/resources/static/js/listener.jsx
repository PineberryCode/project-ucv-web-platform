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

var tabUsers = document.
getElementById('tab-users');

tabSupplier.addEventListener('click', () => {
    ReactDOM.render(<SupplierForm />, overview_x);
});

tabSales.addEventListener('click', () => {
    console.log('UnexpectedError... Sales')
});

tabStock.addEventListener('click', () => {
    google.charts.load('current', {'packages':['corechart','bar']});
    google.charts.setOnLoadCallback(drawStockBarChart);
});

tabUsers.addEventListener('click', () => {
    console.log('UnexpectedError... Users')
});