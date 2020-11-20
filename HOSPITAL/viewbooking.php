<?php include("header.php"); ?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<div align="center">
<?php 
 include("connect.php");
?>
<style>
select{width:200px;}
</style>

<script>


          var xmlHttp;
            function show(to){
				//alert(to)
                if (typeof XMLHttpRequest != "undefined"){
                xmlHttp= new XMLHttpRequest();
                }
                else if (window.ActiveXObject){
                    xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
                }
                if (xmlHttp==null){
                    alert("Browser does not support XMLHTTP Request")
                    return;
                }
                var url="doc.php";
                url +="?c=" +to
                xmlHttp.onreadystatechange = stateChange;
                xmlHttp.open("GET", url, true);
                xmlHttp.send(null);
            }
            
            
            function stateChange(){
                if(xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){
                   document.getElementById("doct").innerHTML=xmlHttp.responseText  
                   // document.getElementById("doct").value=xmlHttp.responseText   
					//alert(xmlHttp.responseText);
                }
	}

</script>

<body>
<form method="post" action="">
<p>
  <label>SELECT CATEGORY</label>
  <br /><br />
  <select required name="department" size="1" onchange="show(this.value)">
    <option value="">--select--</option>
    <?php
$str="select * from department ";
$res=mysql_query($str);
while($res1=mysql_fetch_array($res))
{
	
?>
    <option value="<?php echo $res1[0] ?>"><?php echo $res1[1] ?></option>
    <?php }?>
    
    
  </select>
  </p>
<p><div>
  <label>SELECT DOCTOR</label>
</p>
<p>
  <select name="doct" id="doct" required >
  <option value="">--select--</option>
  </select>
  <br />
  <br />
  
  <input name="submit" type="submit" value="OK" /><BR />
  <BR /><BR /></p></div>
  <?php
if(isset($_POST['submit'])){
	
	$did=$_POST['doct'];
?>
  <br />
  <br />
  
  <label>Booking details</label>
  <br />
</p>
<table width="500" border="1" class="icon-table">
  <tr>
    <th scope="col">No.</th>
    <th scope="col">Patient name</th>
   
 
     <th scope="col">Date</th>
    
    <th scope="col">Timeslot</th>
  </tr>
    <?php
  $i=1;
   $s=mysql_query("select patient.*,time.*,booking.* from booking,patient,time where time.did='$did' and time.tid=booking.tid and booking.pid=patient.log_id");
   if(mysql_num_rows($s)>0)
   {
	   while($r=mysql_fetch_array($s))
	   {
		   ?>
           <tr>
           <td> <?php echo $i++;?></td>
           <td> <?php echo $r[1];?></td>
         
           <td> <?php echo $r[11];?></td>
       
          <td>Start: <?php echo $r[9];?> End <?php echo $r[10]?></td>
          </tr>
           <?php
	   }
   }
   else{
	   
	echo "no data";   
   }
  
   ?> 
</table>
<?php
}
?>
</form>

</body>
</div>
</html>
<?php include("footer.php"); ?>
