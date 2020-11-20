
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>
<style>
body
{background-image:url("doc.jpg");}
h1{color:#FFF;}
h2{color:#FFF;}
</style>
<body>

<?php
include("connect.php");
if (isset($_POST['button']))
{
	$username=$_POST['user'];
	$password=$_POST['psd'];
	

$res=mysql_query("select * from login where Username='$username' and Password='$password'");
if(mysql_num_rows($res)>0){
	header("location:homepage.php");
	
}else{
    ?>
    <script>
	alert("INVALID USERNAME AND PASSWORD");
	</script>
  
    <?php
}
}
?>
<form  id="form1" name="form1" method="post" action="">
<h1 align="center">Login</h1>
  <table width="200" border="0" align="center">
    <tr>
      <td><h2>Username</h2></td>
      <td><label for="textfield"></label>
      <input type="text" name="user" id="textfield" required="required"/></td>
    </tr>
    <tr>
      <td><h2>Password</h2></td>
      <td><label for="textfield2"></label>
      <input type="password" name="psd" id="textfield2" required="required"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" name="button" id="button" value="Login" /></td>
    </tr>
  </table>
</form>
</body>
</html>


