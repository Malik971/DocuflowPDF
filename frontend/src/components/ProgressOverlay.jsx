import React from "react";
import { LinearProgress, Box, Typography } from "@mui/material";

function LinearProgressWithLabel({ value }) {
  return (
    <Box sx={{ display: "flex", alignItems: "center" }}>
      <Box sx={{ width: "100%", mr: 1 }}>
        <LinearProgress variant="determinate" value={value} />
      </Box>
      <Box sx={{ minWidth: 35 }}>
        <Typography variant="body2" color="text.secondary">{`${Math.round(value)}%`}</Typography>
      </Box>
    </Box>
  );
}

const ProgressOverlay = ({ isVisible, progress = 50, loadingMessage }) => {
  if (!isVisible) return null;
  return (
    <Box
      sx={{
        position: "fixed",
        top: 0,
        left: 0,
        width: "100vw",
        height: "100vh",
        backgroundColor: "rgba(255, 255, 255, 0.7)",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        zIndex: 9999,
      }}
    >
      <Typography variant="h6" sx={{ mb: 2 }}>
        {loadingMessage || "Votre PDF est en cours de génération..."}
      </Typography>
      <Box sx={{ width: "60%" }}>
        <LinearProgressWithLabel value={progress} />
      </Box>
    </Box>
  );
};

export default ProgressOverlay;
