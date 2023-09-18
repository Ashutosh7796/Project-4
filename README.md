# Project-4
use salaryfy;
ALTER TABLE `documents`
ADD UNIQUE KEY `unique_pphoto` (`user_user_id`, `document_type`);
