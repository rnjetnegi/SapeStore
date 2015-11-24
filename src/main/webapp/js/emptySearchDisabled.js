//Author : Siddhant and Aditi
//The code disables the search button until there is some text in it.
//The button is disabled for blank spaces as well.

$(document).ready(function(){
    $('.searchBtn').attr('disabled',true);
    
    $('#searchByText').keyup(function(){
        if($(this).val().length !=0  && $(this).val().trim()){
            $('.searchBtn').attr('disabled', false);
        }
        else
        {
            $('.searchBtn').attr('disabled', true);        
        }
    });
});
