//const recoverCategories = JSON.parse(localStorage.getItem('Categories'));
//import { Chart } from "react-google-charts";

const recoverNameAndQuantitiesProduct = JSON.parse(localStorage.getItem('ObjectNameLargeAndQuantities'));

//let arrCategories = recoverCategories.split(",");
let arrRowNameAndQuantitiesProduct = recoverNameAndQuantitiesProduct.split(",");

//let dataCategories = [['Category']];
let dataNamesQuantities = [['Product', 'Quantity']];

//for (let x of arrCategories) {dataCategories.push([x]);}

for (let x of arrRowNameAndQuantitiesProduct) {
    let names = x.split("-")[0];
    let quantities = parseInt(x.split("-")[1]);
    dataNamesQuantities.push([names,quantities]);
}

function DrawStockBarChart () {
    
    //var data = new google.visualization.arrayToDataTable (dataNamesQuantities);
    /*var options = {
        title: 'STOCK',
        chartArea: {width: '50%'},
        colors: ['#082573'],
        hAxis: {
          title: 'Quantity',
          minValue: 0,
          titleTextStyle: {
            bold: true
          }
        },
        vAxis: {
          title: 'Product',
          titleTextStyle: {
            bold: true
          }
        },
        legend: {
            position: 'right'
        }
    };*/

    return (
      /*<Chart 
        /*chartType="ScatterChart"
        data={[["Age", "Weight"], [4, 5.5], [8, 12]]}
        width="100%"
        height="400px"
        legendToggle
      />*/
      <></>
    );
    //var chart = new google.visualization.BarChart(document.getElementById('overview-x'));

    //chart.draw(data, options);
}
//Render the graphic with ReactDOM 
//export default drawStockBarChart