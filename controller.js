//import models
const {Professional} = require("./models/professional");

const listProfessionals = async () => {
    const docs = await Professional.find({});
    console.log(docs);
    return docs;
}

const listProfessional = (id) => {
    Professional.find({_id: id}, (err, docs) => {
        return docs[0];
    });
    return null;
}

const addProfessional = (prof) => {
    const professional = new Professional(prof);
    const res = professional.save();
    return professional;
}

const updateProfessional = async (id, updatedProfessional) => {
    let professional = await Professional.findOne({_id: id});
    if(professional === null || professional === undefined) {
        return false;
    }
    for(const key of Object.keys(updatedProfessional)) {
        professional[key] = updatedProfessional[key];
    }
    professional.updatedAt = Date.now();
    professional.save();
    return true;
}

const deleteProfessional = async (id) => {
    try {
        let professional = await Professional.findOne({_id: id});
        if(professional === undefined || professional === null) {
            return null;
        }
        Professional.deleteOne({_id: id}, (err) => {
            return {"err": err};
        });
        return {"msg": "success"};
    } catch(err) {
        console.log("err: " + err);
        return null;
    }

}

module.exports = {listProfessionals, listProfessional, addProfessional, updateProfessional, deleteProfessional};