<?php
include("../connect.php");
$doctor=array();
$id=$_GET["uid"];
$str="select * from doctor,department where log_id='$id' and department.depid=doctor.depid ";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
while($res1=mysql_fetch_array($res))
{

$doctor["did"]=$res1['did'];
$doctor["Name"]=$res1['Name'];
$doctor["place"]=$res1['place'];
$doctor["hospital"]=$res1['hospital'];
$doctor["depid"]=$res1['depid'];
$doctor["experience"]=$res1['experience'];
$doctor["email_id"]=$res1['email_id'];
$doctor["phone_no"]=$res1['phone_no'];
$doctor["dep"]=$res1['department'];
$doctor["reg"]=$res1['reg_no'];

}
$doctor["success"] = 1;
}
else
{
$doctor["success"] = 0;
}
echo json_encode($doctor);
?>

