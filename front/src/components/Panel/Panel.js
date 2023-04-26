// Panel.js
import React from 'react';

import Sender from "../Sender/Sender";

function Panel(props) {
    const {points, onAddPoint,} = props;

    const handleSubmit = () => {
        Sender.sendPoints(points)
    };

    return (
        <div style={{backgroundColor: '#e3e3e3', height: '100%'}}>
            <h2 style={{marginTop: 0}}>Panel</h2>
            <button onClick={() => onAddPoint([55.751211, 37.621651])}>Add Point 1</button>
            <button onClick={() => onAddPoint([55.751270, 37.621632])}>Add Point 2</button>
            <button onClick={() => onAddPoint([55.751272, 37.621651])}>Add Point 3</button>
            <button onClick={handleSubmit}>Send Points</button>
            <h3>Points</h3>
            <ul>
                {points.map((point, index) => (
                    <li key={index}>{`Point ${index + 1}: ${point[0]}, ${point[1]}`}</li>
                ))}
            </ul>
        </div>
    );
}

export default Panel;