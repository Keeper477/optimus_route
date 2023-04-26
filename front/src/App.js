import React, { useState } from 'react';
import Map from './components/Map/Map';
import Panel from './components/Panel/Panel';
import 'leaflet/dist/leaflet.css';

function App() {
    const [points, setPoints] = useState([]);

    const handleAddPoint = (point) => {
        setPoints([...points, point]);
    };

    return (
        <div style={{ display: 'flex', height: '100vh' }}>
            <div style={{ flex: 3 }}>
                <Map points={points} setPoints={setPoints} />
            </div>
            <div style={{ flex: 1 }}>
                <Panel points={points} onAddPoint={handleAddPoint} />
            </div>
        </div>
    );
}

export default App;

