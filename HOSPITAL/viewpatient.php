<?php include("header.php"); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body><label><h3>View patient</h3><table width="500" border="1">
  <tr>
    <td>No.</td>
    <td>Name</td>
   <td>Age</td>
   
   
    <td>Phone no.</td>
    <td>Place</td>
   
      </tr>
    <?php
   include("connect.php");
   $s=mysql_query("select * from patient");
   if(mysql_num_rows($s)>0)
   {
	   while($r=mysql_fetch_array($s))
	   {
		   ?>
           <tr>
           <td> <?php echo $r["pid"];?></td>
           <td> <?php echo $r["name"];?></td>
           <td> <?php echo $r["age"];?></td>
          
       <td> <?php echo $r["phone_no"];?></td>
          <td> <?php echo $r["place"];?></td>
          </tr>
           <?php
	   }
   }
           
           
     
   ?> 
  </table>
</label>
</body>
</html>
<?php include("footer.php"); ?>