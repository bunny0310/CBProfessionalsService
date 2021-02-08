const express = require('express');
const router = express.Router();
const {
    listProfessionals,
    listProfessional,
    addProfessional,
    updateProfessional,
    deleteProfessional
} = require('./controller');

 const validateProfessionalJSON = (professional) => {
    for(const key of Object.keys(professional)) {
        if(key === 'schoolEmail' || key === 'schoolName') {
            continue;
        }
        if(professional[key] === undefined || professional[key] === null || professional[key] === '') {
            return false;
        }
    }
    return true;
}

router.post('/getInChunks', (req, res) => {
    const limit = req.body.limit;
    const offset = req.body.offset;
    const docs = listProfessionals();
    if (limit === undefined || offset === undefined || limit === null || offset === null) {
        return res.status(400).json({"msg": "incorrect JSON"});
    }
    return res.status(200).json({data: docs.slice(offset, offset + limit), count: docs.length});
});

router.get('/', (req, res) => {
    listProfessionals()
    .then((docs) => {
        return res.status(200).json({data: docs, count: docs.length});
    })
})

router.get('/:id', (req, res) => {
    const id = req.params['id'];
    if (id === undefined || id === null) {
        return res.status(400).json({"msg": "incorrect json"});
    }
    const prof = listProfessional(id);
    return res.status(200).json({data: prof});
})

router.post('/', (req, res) => {
    const json = req.body;
    const professional = {
         firstName : json.firstName,
         lastName : json.lastName,
         workEmail : json.workEmail,
         schoolEmail : json.schoolEmail,
         schoolName : json.schoolName,
         company : json.company,
         jobTitle : json.jobTitle
    }
    if (!validateProfessionalJSON(professional)) {
        return res.status(400).json({"msg": "incorrect json"});
    }
    const result = addProfessional(professional);
    return res.status(200).json({"msg" : result});
});

router.post('/addMultiple', (req, res) => {
    const professionals = req.body;
    for(const professional of professionals) {
        if (!validateProfessionalJSON(professional)) {
            return res.status(400).json({"msg": "incorrect json for professional with work email " + professional.workEmail});
        }
        addProfessional(professional);
    }
    return res.status(200).json({"msg": "professionals added"});
})

router.put('/:id', (req, res) => {
    const professional = req.body;
    if (!validateProfessionalJSON(professional)) {
        return res.status(400).json({"msg": "incorrect JSON"});
    }
    updateProfessional(req.params["id"], professional)
    .then((msg, status) => {
        if (msg === false) {
            return res.status(400).json({"msg": "professional not found"});
        }
        return res.status(200).json({"msg": msg, "status": status});
    })
    .catch((err) => {
        return res.status(400).json({"msg": "professional not found", "err": err});
    })
});

router.delete('/:id', (req, res) => {
    const id = req.params['id'];
    const result = deleteProfessional(id);
    if(result === null) {
        return res.status(400).json({"msg": "professional not found!"});
    }
    return res.status(200).json(result);
})



module.exports = router;

