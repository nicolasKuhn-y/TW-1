document.addEventListener('DOMContentLoaded', () => {
    getCalendarData().then(response => {
            const data = response.map(reserve => ({title: reserve.hospitalName, start: reserve.date}))

            const calendarEl = document.getElementById('calendar');

            const calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                locale: 'es',
                events: data
            });

            calendar.render();
        }
    )
});


async function getCalendarData() {
    const endpoint = "/TW_1_war_exploded/reserves-calendar"

    const response = await fetch(endpoint)


    return response.json()
}