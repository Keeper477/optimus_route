// Map.js
import React from 'react';
import {MapContainer, Marker, Popup, TileLayer} from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import icon from '../Icon/icon.js';
import MarkerClusterGroup from 'react-leaflet-markercluster';

const defaultPosition = [55.751270, 37.621651];

function Map(props) {
    const {points, setPoints} = props;

    const handleMarkerDragEnd = (event, index) => {
        const newPoints = [...points];
        newPoints[index] = [event.target._latlng.lat, event.target._latlng.lng];
        setPoints(newPoints);
    };

    return (
        <MapContainer center={defaultPosition} zoom={13} scrollWheelZoom={false}
                      style={{height: '100%', width: '100%'}}>
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"/>
            <MarkerClusterGroup>
                {points.map((point, index) => (
                    <Marker key={index} icon={icon} position={point} draggable={true}
                            eventHandlers={{dragend: (event) => handleMarkerDragEnd(event, index)}}>
                        <Popup>{`Point ${index + 1}`}</Popup>
                    </Marker>
                ))}
            </MarkerClusterGroup>
        </MapContainer>
    );
}

export default Map;