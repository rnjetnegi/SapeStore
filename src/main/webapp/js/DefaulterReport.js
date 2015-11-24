function getEmail()
{
	var Values="";
	$('input:button').each(function() {
		if(this.name!=null)
		{
			Values=Values+this.name;
			}
		Values=Values+":";
		});

	
	$.ajax({
		type : "GET",
		url  : "/SapeStore/admin/sendEmail",
		dataType : 'text/javascript',
		data : {'emailIds' : Values},
		success : function(result){
			alert("success");}
		});
	document.getElementById("message").innerHTML="Email sent successfully.";
	
}