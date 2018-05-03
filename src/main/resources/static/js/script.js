$(document).ready(function () {
    $('.hideable').hide();
    $('#newClientFrom').hide();
    $('#oldClientFrom').hide();

    $('button').click(function() {
        $(this).siblings().removeClass('btn-dark');        
        $(this).addClass('btn-dark');
    });

    $('.functionButton').click(function() {
        switch($(this).attr('id')) {
            case 'newClient':
            clearForms();
            $('#newClientFrom').show();
            break;
            case 'oldClient':
            clearForms();
            $('#oldClientFrom').show();
            break;
            case 'callCenter':
            clearForms();
            $('#callCenterForm').show();
            break;
            case 'dras':
            clearForms();
            $('#drasForm').show();
            break;
            case 'orders':
            clearForms();
            $('#ordersForm').show();
            break;
            case 'delivery':
            clearForms();
            $('#deliveryForm').show();
            break;
        }
    });

    $('.clientFromButton').click(function() {
        switch($(this).attr('id')) {
            case 'newClientSalon':
            $('.hideable').hide();
            $('form').find("input[type=text], textarea").val("");
            $('form button').removeClass('btn-dark');    
            $('#newClientSalonForm').show();
            break;
            case 'newClientService':
            $('.hideable').hide();
            $('form').find("input[type=text], textarea").val("");
            $('form button').removeClass('btn-dark');
            $('#newClientServiceForm').show();
            break;
            case 'newClientTelephone':
            $('.hideable').hide();
            $('form').find("input[type=text], textarea").val("");
            $('form button').removeClass('btn-dark');
            $('#newClientTelephoneForm').show();
            break;
            case 'newClientMail':
            $('.hideable').hide();
            $('form').find("input[type=text], textarea").val("");
            $('form button').removeClass('btn-dark');
            $('#newClientMailForm').show();
            break;
            case 'oldClientMeeting':
            $('.hideable').hide();
            $('form').find("input[type=text], textarea").val("");
            $('form button').removeClass('btn-dark');
            $('#oldClientMeetingForm').show();
            break;
            case 'oldClientTelephone':
            $('.hideable').hide();
            $('form').find("input[type=text], textarea").val("");
            $('form button').removeClass('btn-dark');
            $('#oldClientTelephoneForm').show();
            break;
            case 'oldClientMail':
            $('.hideable').hide();
            $('form').find("input[type=text], textarea").val("");
            $('form button').removeClass('btn-dark');
            $('#oldClientMailForm').show();
            break;
        }
    });

    $('form button').click(function() {
        if($(this).attr('id')!='misc') {
            $(this).siblings('input').val($(this).val());
        }
    });

    $('.submit').click(function(){
        $(this).parent().append($('#fromSample').html());
        $(this).siblings('#overlay').show();
    });

    
    $('.hideable').on('click', '.cancel', function() {
        $(this).siblings('.submit').popover('hide');
        $(this).parent('div').parent('.container').parent('#overlay').remove();
    });

    $('.hideable').on('click', '#overlay .container button', function() {
        $(this).siblings().removeClass('btn-dark');        
        $(this).addClass('btn-dark');
        $(this).siblings('input').val($(this).val());
    });

    $('.hideable').on('click', '#overlay .container div .submit', function() {
        $(this).popover('show');
    });
    
    function clearForms() {
        $('.hideable').hide();
        $('.clientFromButton').removeClass('btn-dark');   
        $('.from').hide();
        $(".misc").val("");
    }
})