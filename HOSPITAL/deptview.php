<?php include("header.php"); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<div align="center"
<?php 
 include("connect.php");
?>
<body>
<form id="form1" name="form1" method="post" action="">
<label><H3>DEPARTMENT LIST</H3></label>

  <table width="500" border="4" class="icon-table">
    <tr>
    <td>S.no</td>
      <td>Departments</td>
      </tr>
   <?php
   include("connect.php");
   $s=mysql_query("select * from department");
   if(mysql_num_rows($s)>0)
   {
	   while($r=mysql_fetch_array($s))
	   {
		   ?>
           <tr>
           <td> <?php echo $r["depid"];?></td>
           <td> <?php echo $r["department"];?></td>
       </tr>
           <?php
	   }
   }
           
           
     
   ?> 
    
     </table>
     
</form>
</body></div>
</html><?php include("footer.php"); ?>
      
>