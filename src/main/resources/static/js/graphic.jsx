
function DrawStockBarChart () {

  const [chartData, setChartData] = useState([]);

  const recoverNameAndQuantitiesProduct = JSON.parse(localStorage.getItem('ObjectNameLargeAndQuantities'));
  let arrRowNameAndQuantitiesProduct = recoverNameAndQuantitiesProduct.split(",");
  let dataNamesQuantities = [['Products', 'Quantity']];
  for (let x of arrRowNameAndQuantitiesProduct) {
    let names = x.split("-")[0];
    let quantities = parseInt(x.split("-")[1]);
    dataNamesQuantities.push([names,quantities]);
  }

  const recoverCategories = JSON.parse(localStorage.getItem('Categories'));
  let arrRowCategories = recoverCategories.split(",");
  let dataCategories = [['Category']];

  for (let c of arrRowCategories) {
    let category = c.split('-')[0];
    dataCategories.push([category]);
  }

  var sliceCategory = dataCategories.slice(1, dataCategories.length);
  
  const handleGroupButtonClick = (category) => {

    const form = document.getElementById('form-send-category');
    const formData = new FormData(form);

    formData.append('button-category', category);

    fetch ('/restricted/control-panel/request-category', {
      method: 'POST',
      body: formData
    })
    .then(response => {
      console.log(response);
      const recover_cookie = getCookie("ButtonCategory");
    
      var first_time = recover_cookie.includes('%80') 
      ? recover_cookie.replaceAll('%80','-')
      : recover_cookie;
      var second_time = first_time.includes('%20')
      ? first_time.replaceAll('%20',' ')
      : first_time;

      var third_time = second_time.includes('%0')
      ? second_time.replaceAll('%0',',')
      : second_time;

      let arrRow = third_time.split(',');
      let arrayDataProducts = [['Category', 'Quantity']];
      for (let h of arrRow) {
        let name = h.split('-')[0];
        let quantity = parseInt(h.split('-')[1]);

        arrayDataProducts.push([name,quantity]);
      }

      setChartData(arrayDataProducts);
      DrawChart(arrayDataProducts);
    })
    .catch(error => {
      console.log(error);
    });
  }

  const DrawChart = (chartData) => {
    console.log(chartData);
    google.charts.load('current', {'packages':['corechart','bar']});
    google.charts.setOnLoadCallback(() => {
    
      var data = new google.visualization.arrayToDataTable (chartData);
      
      const options = {
        title: 'STOCK',
        chartArea: {width: '40%', height: '120%'},
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

      var chartContainer = document.getElementById('chart-container');

      if (chartContainer) {
        var chart = new google.visualization.BarChart(chartContainer);
        chart.draw(data, options);
      }
    });
  }

  useEffect(() => {
    setChartData(chartData);
    DrawChart(chartData);
  }, []);

  function handleStockReport(e) {
    e.preventDefault();
    fetch ("/restricted/control-panel/stock-report-export-pdf", {
      method: 'POST'
    })
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.blob();
    })
    .then(blop => {
      const url = window.URL.createObjectURL(new Blob([blop]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'stock-report.pdf');
      document.body.appendChild(link);
      link.click();
      link.parentNode.removeChild(link);
    })
    .catch(error => {
      console.log('Fetch error: ', error);
    });
  }

  function handleSaleReport(e) {
    e.preventDefault();

    fetch ('/restricted/control-panel/sale-report-export-pdf', {
      method: 'POST'
    })
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.blob();
    }).then(blop => {
      const url = window.URL.createObjectURL(new Blob([blop]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'sale-report.pdf');
      document.body.appendChild(link);
      link.click();
      link.parentNode.removeChild(link);
    })
    .catch(error => {
      console.log('Fetch error: ', error);
    });
  }

  return (
    <>
      <div 
      className="btn-group p-3 mb-3 position-static d-block" 
      role="group" 
      aria-label="btn-group-report">
        <button 
        type="button" 
        className="btn btn-info"
        onClick={(e) => handleStockReport(e)}>
        Stock Report
        </button>
        <button 
        type="button" 
        className="btn btn-info"
        onClick={(e) => handleSaleReport(e)}>
        Sale Report
        </button>
      </div>
      <div 
      className="btn-group p-3 mb-4 position-static" 
      role="group" 
      aria-label="btn-group-select-filter">
        <form id="form-send-category">
        {sliceCategory.map((category, index) => (
          <button
          key={index}
          name="button-category"
          id={category} 
          type="button" 
          className="btn btn-primary me-2"
          onClick={() => handleGroupButtonClick(category)}
          >
            {category}
          </button>
        ))}
        </form>
      </div>
      <div 
      id="chart-container" 
      className="container position-absolute">
      </div>
    </>
  );
}