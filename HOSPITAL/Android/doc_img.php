<?php
$lid=$_POST['lid'];
$fname=$lid.".jpg";
$res=array();
if(move_uploaded_file($_FILES['photo']['tmp_name'],"../dimages/$fname"))
{

$res['status']='ok';
}
echo json_encode($res);

?>