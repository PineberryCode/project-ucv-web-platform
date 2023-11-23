
/*const recoverNameAndQuantitiesProduct = JSON.parse(localStorage.getItem('ObjectNameLargeAndQuantities'));

let arrRowNameAndQuantitiesProduct = recoverNameAndQuantitiesProduct.split(",");

let dataNamesQuantities = [['Product', 'Quantity']];

for (let x of arrRowNameAndQuantitiesProduct) {
    let names = x.split("-")[0];
    let quantities = parseInt(x.split("-")[1]);
    dataNamesQuantities.push([names,quantities]);
}*/

function DrawStockBarChart () {

  const [graphic, setGraphic] = useState(null);
  useEffect(() => {
    google.charts.load('current', {'packages':['corechart','bar']});
    google.charts.setOnLoadCallback(() => {
      const recoverNameAndQuantitiesProduct = JSON.parse(localStorage.getItem('ObjectNameLargeAndQuantities'));

      let arrRowNameAndQuantitiesProduct = recoverNameAndQuantitiesProduct.split(",");

      let dataNamesQuantities = [['Product', 'Quantity']];

      for (let x of arrRowNameAndQuantitiesProduct) {
        let names = x.split("-")[0];
        let quantities = parseInt(x.split("-")[1]);
        dataNamesQuantities.push([names,quantities]);
      }
      var data = new google.visualization.arrayToDataTable (dataNamesQuantities);
      
      const options = {
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
      };
      var chart = new google.visualization.BarChart(document.getElementById('root'));
      chart.draw(data, options);
      setGraphic(chart);
      /*setGraphic(
        new google.visualization.BarChart(document.getElementById('root'))
      );
      graphic.draw(data, options);*/
    });
  },[]);

  return (
    <>
      {graphic}
    </>
  );
    
    /*var data = new google.visualization.arrayToDataTable (dataNamesQuantities);
    const options = {
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
    };

    var chart = new google.visualization.BarChart(document.getElementById('root'));
    chart.draw(data, options);

    google.charts.load('current', {'packages':['corechart','bar']});
    google.charts.setOnLoadCallback(DrawStockBarChart);*/
}