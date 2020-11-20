<?php
$did=$_GET['c'];
include("connect.php");
?>

<select name="doct" size="1">
    <option value="select">--select--</option>
    <?php
$str="select * from doctor where depid='$did'";
$res=mysql_query($str);
while($res1=mysql_fetch_array($res))
{
	
?>
    <option value="<?php echo $res1[0] ?>"><?php echo $res1[1] ?></option>
    <?php }?>
    
    
  </select>