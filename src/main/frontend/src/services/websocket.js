let socket;

export const connectWebSocket = (userId, onMessageReceived) => {
    socket = new WebSocket(`ws://localhost:8080/ws/notifications/${userId}`);

    socket.onopen = () => {
        console.log('WebSocket connected');
    };

    socket.onmessage = (event) => {
        const message = JSON.parse(event.data);
        onMessageReceived(message);
    };

    socket.onclose = () => {
        console.log('WebSocket disconnected');
    };

    socket.onerror = (error) => {
        console.error('WebSocket error:', error);
    };
};

export const disconnectWebSocket = () => {
    if (socket) {
        socket.close();
    }
};

export const sendMessage = (message) => {
    if (socket && socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify(message));
    }
};
