import React, { useEffect, useState } from 'react';
import Header from '../components/Header';
import PharmacySelect from '../components/PharmacySelect';
import MedicinesTable from '../components/MedicinesTable';
import DateTime from '../components/DateTime';
import { getPharmacies, getMedicinesByPharmacy } from '../services/api';

const Home = () => {
    const [pharmacies, setPharmacies] = useState([]);
    const [selectedPharmacy, setSelectedPharmacy] = useState(null);
    const [medicines, setMedicines] = useState([]);

    useEffect(() => {
        getPharmacies().then(setPharmacies).catch(console.error);
    }, []);

    const handlePharmacySelect = (pharmacyId) => {
        if (pharmacyId) {
            setSelectedPharmacy(pharmacyId);
            getMedicinesByPharmacy(pharmacyId).then(setMedicines).catch(console.error);
        } else {
            setSelectedPharmacy(null);
            setMedicines([]);
        }
    };

    return (
        <div style={{ textAlign: 'center' }}>
            <Header />
            <PharmacySelect pharmacies={pharmacies} onSelect={handlePharmacySelect} />
            {medicines.length > 0 && <MedicinesTable medicines={medicines}/>}
            <h2>Текущая дата и время:</h2>
            <DateTime />
        </div>
    );
};

export default Home;