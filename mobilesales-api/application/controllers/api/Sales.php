<?php
use Restserver\Libraries\REST_Controller;
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';

class Sales extends REST_Controller
{

    public function __construct(){
        parent::__construct();
        $this->load->model('Sales_model');
        //$this->load->model('Mahasiswa_model');
    }

    public function upload_get(){
       
        $upload = $this->Sales_model->getUpload();

        if($upload){
        $this->response([
            'status' => true,
            'message' => 'data upload',
            'data' => $upload
        ], REST_Controller::HTTP_OK);
    } else {
        $this->response([
            'status' => false,
            'message' => 'data not found'
        ], REST_Controller::HTTP_NOT_FOUND);
    }
}

    public function pickup_get(){
       
            $pickup = $this->Sales_model->getPickUp();

            if($pickup){
            $this->response([
                'status' => true,
                'message' => 'data pick up',
                'data' => $pickup
            ], REST_Controller::HTTP_OK);
        } else {
            $this->response([
                'status' => false,
                'message' => 'data not found'
            ], REST_Controller::HTTP_NOT_FOUND);
        }
    }

   

    public function reason_get(){
        
        $reason = $this->Sales_model->getReason();

        if($reason){
        $this->response([
            'status' => true,
            'message' => 'data pick up reason',
            'data' => $reason
        ], REST_Controller::HTTP_OK);
    } else {
        $this->response([
            'status' => false,
            'message' => 'data not found'
        ], REST_Controller::HTTP_NOT_FOUND);
    }
    }

    public function tes_post(){
        $ktp=$this->uploadImageKTP();
        $npwp=$this->uploadImageNPWP();
        $bukti= $this->uploadImageBukti();

        
        $data= [
                'image_name_pemilik' => $npwp,
                'image_name_ktp' => $ktp,
                'image_name_bukti' => $bukti,
                'location' => $this->input->post('location')
        ];

        if($this->Sales_model->createGambar($data)> 0){
            $this->response([
                'status' => true,
                'message' => 'created successfully'
            ], REST_Controller::HTTP_CREATED);
        } else {
            $this->response([
                'status' => false,
                'message' => ' created failed'
            ], REST_Controller::HTTP_BAD_REQUEST); 
        }
    }

    public function tesedit_post(){
        $id= $this->input->post('id');
        $ktp=$this->uploadImageKTP();
        $npwp=$this->uploadImageNPWP();
        $bukti= $this->uploadImageBukti();

        
        $data= [
                'image_name_pemilik' => $npwp,
                'image_name_ktp' => $ktp,
                'image_name_bukti' => $bukti
        ];

        if($this->Sales_model->updategambar($data, $id)> 0){
            $this->response([
                'status' => true,
                'message' => 'updated successfully'
            ], REST_Controller::HTTP_CREATED);
        } else {
            $this->response([
                'status' => false,
                'message' => 'updated failed'
            ], REST_Controller::HTTP_BAD_REQUEST); 
        }
    }


    public function activity_post(){
        
        $data= [
                'id_form' => $this->input->post('id_form'),
                'id_detail' => $this->input->post('id_detail'),
                'action' => $this->input->post('action'),
                'distribusi_from' => $this->input->post('distribusi_from'),
                'distribusi_to' => $this->input->post('distribusi_to'),
                'ms_code' => $this->input->post('ms_code'),
                'status' => $this->input->post('status'),
                'keterangan' => $this->input->post('keterangan'),
                'created_date' => $this->input->post('created_date'),
                'created_by' => $this->input->post('created_by')
            ];

        if($this->Sales_model->createSales($data)> 0){
            $this->response([
                'status' => true,
                'message' => 'created successfully'
            ], REST_Controller::HTTP_CREATED);
        } else {
            $this->response([
                'status' => false,
                'message' => ' created failed'
            ], REST_Controller::HTTP_BAD_REQUEST); 
        }
    }


    public function uploadImageKTP()
    {
      $config['upload_path'] = './uploads/ktp';
      $config['allowed_types'] = 'gif|jpg|png';
      $config['overwrite'] = true;
      $config['max_size'] = 1024;

      $this->load->library('upload');
      $this->upload->initialize($config);

      if ( ! $this->upload->do_upload('image_name_ktp'))
      {
          //$error = array('error' => $this->upload->display_errors());
          return 'null2';
          //print_r($error);
      //  $this->load->view('upload_form', $error);
      }
      else
      {
        return $this->upload->data("file_name");
      }
    }

    public function uploadImageNPWP()
    {
      $config['upload_path'] = './uploads/npwp';
      $config['allowed_types'] = 'gif|jpg|png';
      $config['overwrite'] = true;
      $config['max_size'] = 1024;

      $this->load->library('upload');
      $this->upload->initialize($config);

      if ( ! $this->upload->do_upload('image_name_pemilik'))
      {
          //$error = array('error' => $this->upload->display_errors());
          //return 'gagal';
          //print_r($error);
      //  $this->load->view('upload_form', $error);
      return 'null1';

      }
      else
      {
        return $this->upload->data("file_name");
      }
    }

    public function uploadImageBukti()
    {
      $config['upload_path'] = './uploads/bukti';
      $config['allowed_types'] = 'gif|jpg|png';
      $config['overwrite'] = true;
      $config['max_size'] = 1024;

      $this->load->library('upload');
      $this->upload->initialize($config);

      if ( ! $this->upload->do_upload('image_name_bukti'))
      {
        
        //$error = array('error' => $this->upload->display_errors());
         return 'null3';
          //print_r($error);
      //  $this->load->view('upload_form', $error);
      }
      else
      {
        return $this->upload->data("file_name");
      }
    }

    private function _deleteImage($id)
    {
        $mahasiswa = $this->Mahasiswa_model->getMahasiswa($id);
        if ($mahasiswa['foto'] != "") {
            $filename = explode(".", $mahasiswa['foto'])[0];
            return array_map('unlink', glob(FCPATH."/uploads/$filename.*"));
        }
    }  

    
    public function upload2_post()
    { 
		    $id= $this->input->post('id');
            
            $data= [ 
                'id' => $id,
                'id_form' => $this->input->post('id_form'),
                'unique_id' => $this->input->post('unique_id'),
                'cabang' => $this->input->post('cabang'),
                'no_case' => $this->input->post('no_case'),
                'cis' => $this->input->post('cis'),
                'name' => $this->input->post('name'),
                'dob' => $this->input->post('dob'),
                'gender' => $this->input->post('gender'),
                'address' => $this->input->post('address'),
                'address_geotag' => $this->input->post('address_geotag'),
                'city' => $this->input->post('city'),
                'city2' => $this->input->post('city2'),
                'phone1' => $this->input->post('phone1'),
                'phone2' => $this->input->post('phone2'),
                'phone3' => $this->input->post('phone3'),
                'phone4' => $this->input->post('phone4'),
                'phone5' => $this->input->post('phone5'),
                'phone6' => $this->input->post('phone6'),
                'phone7' => $this->input->post('phone7'),
                'phone8' => $this->input->post('phone8'),
                'phone9' => $this->input->post('phone9'),
                'phone10' => $this->input->post('phone10'),
                'phone11' => $this->input->post('phone11'),
                'phone12' => $this->input->post('phone12'),
                'phone13' => $this->input->post('phone13'),
                'phone14' => $this->input->post('phone14'),
                'phone15' => $this->input->post('phone15'),
                'id_campaign' => $this->input->post('id_campaign'),
                'type' => $this->input->post('type'),
                'username' => $this->input->post('username'),
                'tm_name' => $this->input->post('tm_name'),
                'tm_code' => $this->input->post('tm_code'),
                'aggree_date' => $this->input->post('aggree_date'),
                'agree_time' => $this->input->post('agree_time'),
                'upload_date' => $this->input->post('upload_date'),
                'tgl_pu' => $this->input->post('tgl_pu'),
                'barcode' => $this->input->post('barcode'),
                'time_pu' => $this->input->post('time_pu'),
                'remark' => $this->input->post('remark'),
                'kode_pick_up' => $this->input->post('kode_pick_up'),
                'kode_gagal_pu' => $this->input->post('kode_gagal_pu'),
                'kode_cancel_pu' => $this->input->post('kode_cancel_pu'),
                'pick_up_date' => $this->input->post('pick_up_date'),
                'ms_code' => $this->input->post('ms_code'),
                'ms_name' => $this->input->post('ms_name'),
                'spv_code' => $this->input->post('spv_code'),
                'asm_code' => $this->input->post('asm_code'),
                'image_type' => $this->input->post('image_type'),
                'image_name_pemilik' => $this->uploadImageNPWP(),

                
                
                'image_name_ktp' => $this->uploadImageKTP(),
                'geo_info_pemilik' => $this->input->post('geo_info_pemilik'),
                'geo_info_ktp' => $this->input->post('geo_info_ktp'),
                'image_name_bukti' => $this->uploadImageBukti(),
                'geo_info_bukti' => $this->input->post('geo_info_bukti'),
                'tanggal_distribusi_asm' => $this->input->post('tanggal_distribusi_asm'),
                'tanggal_distribusi' => $this->input->post('tanggal_distribusi'),
                'note_Iinprocessed' => $this->input->post('note_Iinprocessed'),
                'product' => $this->input->post('product'),
                'tanggal_reschedule' => $this->input->post('tanggal_reschedule'),
                'source_code' => $this->input->post('source_code'),
                'created_date' => $this->input->post('created_date'),
                'status_validasi' => $this->input->post('status_validasi'),
                'validasi_by' => $this->input->post('validasi_by'),
                'status_validasi_admin' => $this->input->post('status_validasi_admin'),
                'created_by' => $this->input->post('created_by'),
                'status_close' => $this->input->post('status_close'),
                'close_date' => $this->input->post('close_date'),
                'closed_by' => $this->input->post('closed_by'),
                'closed_by' => $this->input->post('closed_by'),
                'branch' => $this->input->post('branch'),
                'tanggal_status_terakhir' => $this->input->post('tanggal_status_terakhir'),
                'is_return' => $this->input->post('is_return'),
                'return_date' => $this->input->post('return_date'),
                'status_call' => $this->input->post('status_call'),
                'reason_call' => $this->input->post('reason_call'),
                'other_reason' => $this->input->post('other_reason'),
                'call_by' => $this->input->post('call_by'),
                'call_date' => $this->input->post('call_date'),
                'call_time' => $this->input->post('call_time'),
                'notes' => $this->input->post('notes')
            ];

			
            if($this->Sales_model->updateSales($data, $id)> 0){
        $this->response([
                'status' => true,
                'message' => 'update a new data',
                'data' =>  $data
            ], REST_Controller::HTTP_CREATED);
        } else {
            $this->response([
                'status' => false,
                'message' => 'failed to updated data'
            ], REST_Controller::HTTP_BAD_REQUEST); 
        }
		 echo json_encode($data);
	}
    
}