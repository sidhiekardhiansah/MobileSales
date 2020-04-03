<?php

class Sales_model extends CI_Model
{
   
    public function getPickUp()
    {
         return $this->db->get('ref_status')->result_array();
    }
        
    public function getUpload(){
            return $this->db->get('tbl_upload_ms_detail')->result_array();

    }

    public function getReason()
    {
    //     $query = $this->db->query("select * from `tbl_user`");
    //    return $query->result_array();
        return $this->db->get('ref_pickup_reason')->result_array();
    }

    public function createSales($data)
    {
        $this->db->insert('log_activity_ms', $data);
        return $this->db->affected_rows();
    }

    public function updateSales($data, $id)
    {
        $this->db->update('tbl_upload_ms_detail', $data, ['id'=> $id]);
        // $this->db->set($nrp, $nama, $email, $jurusan);
        //  $this->db->where('id', $id);
        //  $this->db->update('mahasiswa');
        return $this->db->affected_rows();

    }

    public function updategambar($data, $id)
    {
        $this->db->update('tes', $data, ['id'=> $id]);
        // $this->db->set($nrp, $nama, $email, $jurusan);
        //  $this->db->where('id', $id);
        //  $this->db->update('mahasiswa');
        return $this->db->affected_rows();

    }
    
    public function createGambar($data)
    {
        $this->db->insert('tes', $data);
        return $this->db->affected_rows();
    }



    
}