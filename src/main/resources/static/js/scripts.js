document.getElementById('programForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const eventName = document.getElementById('eventName').value;
    const eventDate = document.getElementById('eventDate').value;
    const eventDetails = document.getElementById('eventDetails').value;

    const programData = {
        name: eventName,
        date: eventDate,
        details: eventDetails
    };

    // Sending data to the back-end
    fetch('/api/event-programs', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(programData)
    })
        .then(response => response.json())
        .then(data => {
            // Fetching the generated QR code
            fetch(`/api/qr-codes/${data.id}`, {
                method: 'GET'
            })
                .then(response => response.json())
                .then(qrData => {
                    document.getElementById('qrCode').innerHTML = `<img src="${qrData.qrCodeUrl}" alt="QR Code">`;
                })
                .catch(error => console.error('Error fetching QR code:', error));
        })
        .catch(error => console.error('Error creating program:', error));
});
