import React, { useEffect, useState } from 'react';

const DateTime = () => {
    const [currentDateTime, setCurrentDateTime] = useState('');

    useEffect(() => {
        const interval = setInterval(() => {
            const now = new Date();
            setCurrentDateTime(now.toLocaleString('ru-RU', {
                year: 'numeric',
                month: 'long',
                day: 'numeric',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit',
                hour12: false,
            }));
        }, 1000);

        return () => clearInterval(interval);
    }, []);

    return <div>{currentDateTime}</div>;
};

export default DateTime;