import React from "react";
import useAccommodations from "../../../hooks/useAccommodations";
import {
    Card,
    CardContent,
    Typography,
    Grid,
    CircularProgress,
    Box
} from "@mui/material";

const AccommodationList = () => {
    const accommodations = useAccommodations();

    if (!accommodations.length) {
        return (
            <Box sx={{ display: "flex", justifyContent: "center", mt: 5 }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Grid container spacing={3}>
            {accommodations.map((acc) => (
                <Grid item xs={12} sm={6} md={4} key={acc.id}>
                    <Card>
                        <CardContent>
                            <Typography variant="h6">{acc.name}</Typography>
                            <Typography variant="body2" color="textSecondary">
                                {acc.city}, {acc.country}
                            </Typography>
                            <Typography variant="body2">
                                {acc.description}
                            </Typography>
                        </CardContent>
                    </Card>
                </Grid>
            ))}
        </Grid>
    );
};

export default AccommodationList;
