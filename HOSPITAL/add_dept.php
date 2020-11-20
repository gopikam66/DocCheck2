<?php include("header.php"); ?>
<div align="center">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script type="text/javascript">
function validation(){
	 
}
</script>
</head>

<body>
<?php 
 include("connect.php");
?>

<form id="form1" name="form1" method="post" action="">
  <table width="291" align="center" border="1" class="icon-table">
    <tr>
      <td>DEPARTMENT</td>
      <td><label for="textfield"></label>
      <input type="text" name="textfield" id="textfield" required="required" /></td>
    </tr>
    <tr>
      <td colspan="2"><div align="center">
        <input type="submit" name="submit" id="button" value="Add" />
      </div></td>
    </tr>
    <?php
if(isset($_POST['submit'])){
	
	$dname=$_POST['textfield'];
	$res="insert into department(department) values('$dname')";
	mysql_query($res);
}
?>

  </table>
</form>
</body>
</html>
</div>
<?php include("footer.php"); ?>