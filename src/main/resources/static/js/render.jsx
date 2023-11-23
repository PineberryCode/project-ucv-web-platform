//import { drawStockBarChart } from "./graphic.jsx";
//import { SupplierForm } from "./components/form.jsx";

const root = ReactDOM.createRoot(
    document.getElementById('root')
);

var tabMain = document.
getElementById('tab-main');

var tabSupplier = document.
getElementById('tab-supplier');

var tabSales = document.
getElementById('tab-sales');

var tabStock = document.
getElementById('tab-stock');

var tabEmployee = document.
getElementById('tab-employee');

function getCookie(name) { //Universal
    const cookieString = document.cookie;
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
    root.render(<SupplierContent />);
    document.cookie = 'removed=false; path=/restricted; max-age=3600';
} else {
    console.log('hola');
};

tabMain.addEventListener('click', () => {
    root.render(<DrawStockBarChart />);
})

tabSupplier.addEventListener('click', () => {
    root.render(<SupplierContent />);
});

tabSales.addEventListener('click', () => {
    root.render(<SalesContent />);
});

tabStock.addEventListener('click', () => {
    root.render(<ProductContent />)
});

tabEmployee.addEventListener('click', () => {
    root.render(<EmployeeContent />);
});

root.render(<DrawStockBarChart />);