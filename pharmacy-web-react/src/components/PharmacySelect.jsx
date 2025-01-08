import React from 'react';

const PharmacySelect = ({ pharmacies, onSelect }) => (
    <div>
        <select onChange={(e) => onSelect(e.target.value)}>
            <option value="">Выберите сеть</option>
            {pharmacies.map((pharmacy) => (
                <option key={pharmacy.id} value={pharmacy.id}>
                    {pharmacy.name}
                </option>
            ))}
        </select>
    </div>
);

export default PharmacySelect;