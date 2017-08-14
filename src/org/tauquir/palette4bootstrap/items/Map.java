/*
 * palette4bootstrap - A netbeans palette plugin for the Bootstrap
 * Copyright (c) 2017-2018 Tauquir Ahmed (tauquirahmed93@gmail.com)
 * Licensed under the MIT License.
 */
package org.tauquir.palette4bootstrap.items;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;
import org.tauquir.palette4bootstrap.bsPaletteUtilities;

public class Map implements ActiveEditorDrop {

    // Params common to all mapMode
    private String apiKey = "YOUR_API_KEY";
    private String width = "100%";
    private String height = "320px";

    // Default mapMode
    private String mapMode = "Place";

    // Params for mapMode = Place
    private String modePlaceAddress = "India";
    private String modePlaceZoom = "4";
    private String modePlaceMapType = "roadmap";

    // Params for mapMode = Directions
    private String modeDirectionsOrigin = "Delhi, India";
    private String modeDirectionsDestination = "Mumbai, India";
    private String modeDirectionsZoom = "5";
    private String modeDirectionsMapType = "roadmap";

    // Params for mapMode = Search
    private String modeSearchTerms = "India";
    private String modeSearchZoom = "4";
    private String modeSearchMapType = "roadmap";

    // Params for mapMode = View
    private String modeViewCoordinates = "20.5937,78.9629";
    private String modeViewZoom = "4";
    private String modeViewMapType = "roadmap";

    // Params for mapMode = Street View
    private String modeStreetViewCoordinates = "27.1733511,78.042109";

    public Map() {
    }

    public String generateBody() {
        StringBuilder code = new StringBuilder();
        code.append("<iframe allowfullscreen frameborder=\"0\"");
        code.append(" width=\"").append(width).append("\"");
        code.append(" height=\"").append(height).append("\"");
        code.append(" src=\"");
        switch (mapMode) {
            case "Place":
                code.append(getMapUrlforModePlace(apiKey, modePlaceAddress, modePlaceZoom, modePlaceMapType));
                break;
            case "Directions":
                code.append(getMapUrlforModeDirection(apiKey, modeDirectionsOrigin, modeDirectionsDestination, modeDirectionsZoom, modeDirectionsMapType));
                break;
            case "Search":
                code.append(getMapUrlforModeSearch(apiKey, modeSearchTerms, modeSearchZoom, modeSearchMapType));
                break;
            case "View":
                code.append(getMapUrlforModeView(apiKey, modeViewCoordinates, modeViewZoom, modeViewMapType));
                break;
            case "Street View":
                code.append(getMapUrlforModeStreetView(apiKey, modeStreetViewCoordinates));
                break;
        }
        code.append("\"");
        code.append("></iframe>");
        return code.toString();
    }

    private String getMapUrlforModePlace(String apiKey, String address, String zoomLevel, String mapType) {
        StringBuilder url = new StringBuilder();
        url.append("https://www.google.com/maps/embed/v1/place?key=")
                .append(apiKey)
                .append("&q=").append(address)
                .append("&zoom=").append(zoomLevel);
        if (!mapType.equals("roadmap")) {   // since modePlaceMapType=roadmap is default
            url.append("&maptype=").append(mapType);
        }
        return url.toString();
    }

    private String getMapUrlforModeDirection(String apiKey, String origin, String destination, String zoomLevel, String mapType) {
        StringBuilder url = new StringBuilder();
        url.append("https://www.google.com/maps/embed/v1/directions?key=")
                .append(apiKey)
                .append("&origin=").append(origin)
                .append("&destination=").append(destination)
                .append("&zoom=").append(zoomLevel);
        if (!mapType.equals("roadmap")) {   // since modePlaceMapType=roadmap is default
            url.append("&maptype=").append(mapType);
        }
        return url.toString();
    }

    private String getMapUrlforModeSearch(String apiKey, String searchTerms, String zoomLevel, String mapType) {
        StringBuilder url = new StringBuilder();
        url.append("https://www.google.com/maps/embed/v1/search?key=")
                .append(apiKey)
                .append("&q=")
                .append(searchTerms)
                .append("&zoom=").append(zoomLevel);
        if (!mapType.equals("roadmap")) {   // since modePlaceMapType=roadmap is default
            url.append("&maptype=").append(mapType);
        }
        return url.toString();
    }

    private String getMapUrlforModeView(String apiKey, String coordinates, String zoomLevel, String mapType) {
        StringBuilder url = new StringBuilder();
        url.append("https://www.google.com/maps/embed/v1/view?key=")
                .append(apiKey)
                .append("&center=")
                .append(coordinates)
                .append("&zoom=").append(zoomLevel);
        if (!mapType.equals("roadmap")) {   // since modePlaceMapType=roadmap is default
            url.append("&maptype=").append(mapType);
        }
        return url.toString();
    }

    private String getMapUrlforModeStreetView(String apiKey, String coordinates) {
        StringBuilder url = new StringBuilder();
        url.append("https://www.google.com/maps/embed/v1/streetview?key=")
                .append(apiKey)
                .append("&location=")
                .append(coordinates);
        return url.toString();
    }

    @Override
    public boolean handleTransfer(JTextComponent targetComponent) {
        MapCustomizer c = new MapCustomizer(this);
        boolean accept = c.showDialog();
        if (accept) {
            String body = generateBody();
            try {
                bsPaletteUtilities.insert(body, targetComponent);
            } catch (BadLocationException ble) {
                accept = false;
            }
        }
        return accept;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setMapMode(String mapMode) {
        this.mapMode = mapMode;
    }

    public void setModePlaceAddress(String modePlaceAddress) {
        this.modePlaceAddress = modePlaceAddress;
    }

    public void setModePlaceZoom(String modePlaceZoom) {
        this.modePlaceZoom = modePlaceZoom;
    }

    public void setModePlaceMapType(String modePlaceMapType) {
        this.modePlaceMapType = modePlaceMapType.toLowerCase();
    }

    public void setModeDirectionsOrigin(String modeDirectionsOrigin) {
        this.modeDirectionsOrigin = modeDirectionsOrigin;
    }

    public void setModeDirectionsDestination(String modeDirectionsDestination) {
        this.modeDirectionsDestination = modeDirectionsDestination;
    }

    public void setModeDirectionsZoom(String modeDirectionsZoom) {
        this.modeDirectionsZoom = modeDirectionsZoom;
    }

    public void setModeDirectionsMapType(String modeDirectionsMapType) {
        this.modeDirectionsMapType = modeDirectionsMapType.toLowerCase();
    }

    public void setModeSearchTerms(String modeSearchTerms) {
        this.modeSearchTerms = modeSearchTerms;
    }

    public void setModeSearchZoom(String modeSearchZoom) {
        this.modeSearchZoom = modeSearchZoom;
    }

    public void setModeSearchMapType(String modeSearchMapType) {
        this.modeSearchMapType = modeSearchMapType.toLowerCase();
    }

    public void setModeViewCoordinates(String modeViewCoordinates) {
        this.modeViewCoordinates = modeViewCoordinates;
    }

    public void setModeViewZoom(String modeViewZoom) {
        this.modeViewZoom = modeViewZoom;
    }

    public void setModeViewMapType(String modeViewMapType) {
        this.modeViewMapType = modeViewMapType.toLowerCase();
    }

    public void setModeStreetViewCoordinates(String modeStreetViewCoordinates) {
        this.modeStreetViewCoordinates = modeStreetViewCoordinates;
    }

    public String getMapMode() {
        return mapMode;
    }

    /**
     * Used to reset all parameters of this object to their default values
     * Called on closing the options dialog so that when the dialog is
     * re-opened, old values do not persist
     */
    public void resetParameters() {
        // Params common to all mapMode
        apiKey = "YOUR_API_KEY";
        width = "100%";
        height = "320px";

        // Default mapMode
        mapMode = "Place";

        // Params for mapMode = Place
        modePlaceAddress = "India";
        modePlaceZoom = "4";
        modePlaceMapType = "roadmap";

        // Params for mapMode = Directions
        modeDirectionsOrigin = "Delhi, India";
        modeDirectionsDestination = "Mumbai, India";
        modeDirectionsZoom = "5";
        modeDirectionsMapType = "roadmap";

        // Params for mapMode = Search
        modeSearchTerms = "India";
        modeSearchZoom = "4";
        modeSearchMapType = "roadmap";

        // Params for mapMode = View
        modeViewCoordinates = "20.5937,78.9629";
        modeViewZoom = "4";
        modeViewMapType = "roadmap";

        // Params for mapMode = Street View
        modeStreetViewCoordinates = "27.1733511,78.042109";
    }
}
