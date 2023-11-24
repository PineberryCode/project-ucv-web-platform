
function DrawStockBarChart () {

  const recoverCategories = JSON.parse(localStorage.getItem('Categories'));
  let arrRowCategories = recoverCategories.split(",");
  let dataCategories = [['Category']];

  for (let c of arrRowCategories) {
    let category = c.split('-')[0];
    dataCategories.push([category]);
  }

  var sliceCategory = dataCategories.slice(1, dataCategories.length);
  
  const handleGroupButtonClick = (category) => {
    //e.preventDefault();

    const form = document.getElementById('form-send-category');
    const formData = new FormData(form);

    formData.append('button-category', category);

    fetch ('/restricted/control-panel/request-category', {
      method: 'POST',
      body: formData
    })
    .then(response => {
      console.log(response);
    })
    .catch(error => {
      console.log(error);
    });
  }
  
  useEffect(() => {
    google.charts.load('current', {'packages':['corechart','bar']});
    google.charts.setOnLoadCallback(() => {
      
      const recoverNameAndQuantitiesProduct = JSON.parse(localStorage.getItem('ObjectNameLargeAndQuantities'));
      let arrRowNameAndQuantitiesProduct = recoverNameAndQuantitiesProduct.split(",");
      let dataNamesQuantities = [['Products', 'Quantity']];

      for (let x of arrRowNameAndQuantitiesProduct) {
        let names = x.split("-")[0];
        let quantities = parseInt(x.split("-")[1]);
        dataNamesQuantities.push([names,quantities]);
      }
      var data = new google.visualization.arrayToDataTable (dataNamesQuantities);
      
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
  },[]);

  return (
    <>
      <div className="btn-group p-3 mb-4" role="group" aria-label="Basic example">
        <form id="form-send-category">
        {sliceCategory.map((category, index) => (
          <button
          key={index}
          name="button-category"
          id={category} 
          type="button" 
          className="btn btn-primary me-2"
          onClick={() => handleGroupButtonClick(category[0])}
          >
            {category[0]}
          </button>
        ))}
        </form>
      </div>
      <div 
      id="chart-container" 
      className="container">
      </div>
    </>
  );
}