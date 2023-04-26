import {Icon} from 'leaflet';

import markerIconPng from "leaflet/dist/images/marker-icon.png"

const icon = new Icon({
    iconUrl: markerIconPng,
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    tooltipAnchor: [16, -28],
    shadowUrl: null,
    shadowSize: null,
    shadowAnchor: null
});

export default icon;