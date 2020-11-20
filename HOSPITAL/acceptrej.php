<?php
   include("connect.php");
   
   $dlid=$_GET["dlogid"];
   $tp=$_GET["tp"];
   $email=$_GET['email'];
   echo $dlid.".....".$tp;
   $s=0;
    $details="status:Approved";
   if($tp=="1"){
     $s=mysql_query("update login set Type='doctor' where id='$dlid'");
   }
   else{
	   
    	$details="status:Rejected";
	   	$s=mysql_query("delete from login where id='$dlid'");
	   	$s=mysql_query("delete from doctor where log_id='$dlid'");
   }
   		$sub="Registration status";
  
   require("class.phpmailer.php");

$mail = new PHPMailer(); // create a new object
$mail->IsSMTP(); // enable SMTP
$mail->SMTPDebug = 1; // debugging: 1 = errors and messages, 2 = messages only
$mail->SMTPAuth = true; // authentication enabled
$mail->SMTPSecure = 'tls'; // secure transfer enabled REQUIRED for GMail
$mail->Host = "smtp.gmail.com";
$mail->Port = 587;
$mail->Username = "doccheckgass@gmail.com";  // SMTP username gmail username
$mail->Password = "checkdoc123"; // SMTP password   gmail password

$mail->From = "doccheckgass@gmail.com"; // from email address
$mail->FromName = "doccheckgass@gmail.com"; //from name
$mail->AddAddress($email);                   // to address 

$mail->Subject = $sub;
$mail->Body    = $details;
$mail->AltBody = "";

if(!$mail->Send())
{
	
   ?>
    <script type="text/javascript">
	alert("mail not  send");
	window.location="check doctor.php";
	</script>
   
   <?php
}
else
{
	echo ' send';
	?>
    <script type="text/javascript">
	alert("mail send succesfully ");
	window.location="check doctor.php";
	</script>
   
   <?php
}
?>