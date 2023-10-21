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

tabSupplier.addEventListener('click', () => {
    ReactDOM.render(<SupplierForm />, overview_x);
});

tabSales.addEventListener('click', () => {
    console.log('UnexpectedError... Sales')
});

tabStock.addEventListener('click', () => {
    /*ReactDOM.render( ()=> {

    })*/
    google.charts.load('current', {'packages':['corechart','bar']});
    google.charts.setOnLoadCallback(drawStockBarChart);
});

tabEmployee.addEventListener('click', () => {
    ReactDOM.render(<EmployeeForm />, overview_x);
});