
<?php include("header.php"); ?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
<label><H3>DOCTORS LIST</H3></label>
  <table width="500" border="2">
    <tr>
    <td>S.no</td>
      <td>Name</td>
      
      <td>Place</td>
      <td>Hospital</td>
      <td>Specialization</td>
      <td>Experience</td>
      <TD>Email</TD>
      <TD>Contact no.</TD>
      <TD>Status</TD>
    </tr>
   <?php
   include("connect.php");
   $s=mysql_query("select doctor.*,department.*,login.Type from doctor,department,login where doctor.depid=department.depid and login.id=doctor.log_id");
   if(mysql_num_rows($s)>0)
   {
	   while($r=mysql_fetch_array($s))
	   {
		   ?>
           <tr>
           <td> <?php echo $r["did"];?></td>
           <td> <?php echo $r["Name"];?></td>
           <td> <?php echo $r["place"];?></td>
           <td> <?php echo $r["hospital"];?></td>
           <td> <?php echo $r["department"];?></td>
            <td> <?php echo $r["experience"];?></td>  
         <td> <?php echo $r["email_id"];?></td> 
          <td> <?php echo $r["phone_no"];?></td>
          <td> <?php if($r["Type"]=="doctor_pending"){?>  <a href="acceptrej.php?dlogid=<?php echo $r["log_id"];?>&tp=1&email=<?php echo $r["email_id"];?>">Accept</a>&nbsp;&nbsp;<a href="acceptrej.php?dlogid=<?php echo $r["log_id"];?>&tp=0&email=<?php echo $r["email_id"];?>" >Reject</a>          <?php }
		  else{
		  ?>   <a href="acceptrej.php?dlogid=<?php echo $r["log_id"];?>&tp=0&email=<?php echo $r["email_id"];?>" >Delete</a>        <?php } ?></td>
         
          </tr>
           <?php
	   }
   }
           
           
     
   ?> 
    
     </table>
</form>
</body>
</html><?php include("footer.php"); ?>