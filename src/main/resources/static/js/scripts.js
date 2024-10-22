document.getElementById('saveEventButton').addEventListener('click', function() {
    // Save event logic
    let eventName = document.getElementById('eventName').value;
    let eventDate = document.getElementById('eventDate').value;

    // AJAX request to save event
    fetch('/saveEvent', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: eventName,
            date: eventDate
        })
    })
        .then(response => response.json())
        .then(data => {
            alert("Event saved successfully!");
        })
        .catch(error => {
            console.error("Error saving event:", error);
        });
});

document.getElementById('generateQrButton').addEventListener('click', function() {
    let eventName = document.getElementById('eventName').value;
    let eventDate = document.getElementById('eventDate').value;

    // Create the data for QR code generation (could be a URL or event info)
    let qrData = `Event: ${eventName}, Date: ${eventDate}`;

    // AJAX request to generate QR code
    fetch('/generateQr', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            qrData: qrData
        })
    })
        .then(response => response.json())
        .then(data => {
            // Display the generated QR code
            let qrImage = document.createElement('img');
            qrImage.src = data.qrCodeUrl;  // The URL where the QR code image is located
            document.getElementById('qrCodeContainer').appendChild(qrImage);
        })
        .catch(error => {
            console.error("Error generating QR code:", error);
        });
});
