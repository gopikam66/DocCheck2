<?php include("header.php"); ?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<div align="center">
<body>
<form id="form1" name="form1" method="post" action=""><label>FROM</label>
<table border="1">
<input name="from" type="date" required="required"/>
<label>TO</label>
<input name="to" type="date" required="required"/>

<input name="submit" type="Submit" value="OK" />

</table>
</form>
<label>
<h3>HISTORY</h3>
</label>
<table width="500" border="1" class="icon-table">
  <tr><th>sl.no</th>
    <th scope="col">Doctor's name</th>
    <th scope="col">Patient</th>
    <th scope="col">Date</th>
    <th>Time slot</th>
  </tr>
  <?php
if(isset($_POST['submit'])){
	
	$from=$_POST['from'];
	$to=$_POST['to'];
	//echo $from.$to;
	include("connect.php");
	$s1="select booking.*,doctor.Name,patient.name,time.* from booking,doctor,patient,time where booking.pid=patient.log_id and booking.tid=time.tid and time.did=doctor.log_id and booking.cur_date between '$from' and '$to'";
	//echo $s1;
	$s=mysql_query($s1);
if(mysql_num_rows($s)>0)
   {
	   $i=1;
	   while($r=mysql_fetch_array($s))
	   {
		   ?>
         
  <tr>
    <td> <?php echo $i;?></td>
    <td> <?php echo $r[4];?></td>
    <td> <?php echo $r[5];?></td>
    <td><?php echo $r[3];?></td>
    <td>Start: <?php echo $r[8];?> End <?php echo $r[9]?></td>
  </tr>
  <?php
  $i++;}}
  else{
	   
	echo "no data";   
   }
  ?>
</table>
<?php
}
?>
</body></div>
</html><?php include("footer.php"); ?>