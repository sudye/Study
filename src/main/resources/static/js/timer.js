
$(document).ready(function(){
	let today = new Date();
    let today_offset = new Date(today.getTime() - (today.getTimezoneOffset() * 60000));
    let countdown = today_offset.toISOString();

    $('#timer').countdown({
        date: countdown,
        offset: 9,//timezone offset
        day: 'Day',
        days: 'Days'
    }, function () {
        console.log('Timer Initialized!');
    });	
});
