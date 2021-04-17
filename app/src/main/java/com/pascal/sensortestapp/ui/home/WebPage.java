package com.pascal.sensortestapp.ui.home;

public class WebPage {

        private String web= new StringBuilder()
                .append("<html>\n")
                .append("  <head>\n").append("  " + "  <script type=\"text/javascript\" src=" + "\"https://www.gstatic.com/charts/loader.js\"></script>\n").append(" " +
                "   <script type=\"text/javascript\">\n")
                .append(" " + "     google.charts.load('current', {'packages':['corechart']});\n")
                .append("      google.charts.setOnLoadCallback(drawChart);\n")
                .append("\n").append("      function drawChart() {\n")
                .append("        var data = google.visualization.arrayToDataTable" +
                        "([\n")
                .append("          ['Date', 'Sensor 1', 'Sensor 2'],\n").toString();
               /* .append("          ['2013',  1000,      400],\n")
                .append("          ['2014',  1170,      460],\n")
                .append("          ['2015',  660,       1120],\n")
                .append("          ['2016',  1030,      540], \n").toString();
*/
        //ajouter des points

    private String webEnd =new StringBuilder()
                .append("        ]);\n")
                .append("\n")
                .append("        var options = {\n")
                .append("          title: 'Company Performance',\n")
                .append("          hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},\n")
                .append("          vAxis: {minValue: 0, textPosition:'in'},\n") //Place the text on vAxis 'in'
               // .append("          width: 400 ,\n")
                .append("          chartArea:{left:10,top:0,width:'95%',height:'65%'},\n")
                .append("          legend: {position: 'in', textStyle: {color: 'blue', fontSize: 11}}\n")
                .append("        };\n")
                .append("\n")
                .append("        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));\n")
                .append("        chart.draw(data, options);\n").append("      }\n").append("    </script>\n")
                .append("  </head>\n").append("  <body>\n")
                .append("    <div id=\"chart_div\" style=\"width: 100%; height: 300px;\"></div>\n").append("  </body>\n")
                .append("</html>").toString();

        //Empty Constructor
    public WebPage(){}

    public void setWeb(String date, double sensor1, double sensor2) {
        String web1,point="    [ '"+date+"',   "+sensor1+",  "+sensor2+"], \n";
        web1=this.web+ point;
        this.web = web1;
    }

    public String getWeb() {
        return web;
    }

    public String getWebEnd() {
        return webEnd;
    }
}

