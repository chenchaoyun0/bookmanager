<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="jxl.Cell"%>
<%@ page import="jxl.CellType"%>
<%@ page import="jxl.Sheet"%>
<%@ page import="jxl.Workbook"%>
<%@ page import="jxl.read.biff.BiffException"%>
<%@ page import="com.sttx.bookmanager.po.GetExcelbean" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线预览-${title}</title>
  </head>
 <body >
 <button onclick="javascript:history.back()" class="btn">返回</button>
 <h1 align="center" style="color: red">${title}</h1>
 <div align="center">
 <%
request.setCharacterEncoding("UTF-8");
try {
String path=request.getParameter("filename");
path=URLDecoder.decode(path,"utf-8");
InputStream is = new FileInputStream(path);
Workbook read_workbook = Workbook.getWorkbook(is);
Sheet sheet = read_workbook.getSheet(0);
GetExcelbean cell_style = new GetExcelbean();
int rows = sheet.getRows();
int columns = sheet.getColumns();
int rowspan = 0;
int colspan = 0;
out.print("<table border='1' cellpadding='0' cellspacing='0' style='font-size: 17px;font-weight: bold;border-collapse:collapse' bordercolor='#000000'>");
for (int i = 0; i < rows; i++) {
out.print("<tr>");
for (int j = 0; j < columns ; j++) {
Cell cell = sheet.getCell(j, i);
cell_style.readRange(sheet,i,j);
rowspan = cell_style.getRowspan();
colspan = cell_style.getColspan();
String contents = null;
if(cell.getType() == CellType.EMPTY){
cell_style.setBetweenRowColumn(i,j);
int min_row = cell_style.getMin_row();
int max_row = cell_style.getMax_row();
int min_col = cell_style.getMin_column();
int max_col = cell_style.getMax_column();
if( min_row < i && i <= max_row && min_col <= j && j<= max_col){
j = max_col;
continue;
}
contents = "&nbsp;";
out.print("<td width='"+(8*colspan)+"' rowspan='"+rowspan+"' colspan='"+colspan+"'>"+contents+"</td>");
j += colspan-1;
continue;
} else {
contents = cell.getContents();
out.print("<td align='center'"
+"rowspan='"+rowspan+"'"
+"colspan='"+colspan+"'"
+"bGcolor='"+cell_style.getBgcolor(cell)+"'" 
+"<Font color='"+cell_style.getFontColor(cell)+"'" 
+"height='25'"
+" width='"+(2000*colspan)+"'>"
+contents+"</Font></td>");
j += colspan-1;
continue;
}
}
out.print("</tr>");
}
read_workbook.close();
is.close();
} catch (FileNotFoundException e) {
e.printStackTrace();
} catch (BiffException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
%> 
</div>
 </body>

</html>
