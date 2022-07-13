document.addEventListener('DOMContentLoaded', () => {
    const options = {
        enableHighAccuracy: true,
        timeout: 5000,
        maximumAge: 0
    };

    function success(pos) {
        const coordinates = pos.coords;

        const element = document.getElementById('coordinates')

        element.href += `?lat=${coordinates.latitude}&long=${coordinates.longitude}`
    }

    function error(err) {
        console.warn(`ERROR(${err.code}): ${err.message}`);
    }

    navigator.geolocation.getCurrentPosition(success, error, options);
});



