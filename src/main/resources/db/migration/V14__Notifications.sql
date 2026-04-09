CREATE TABLE notifications (
   id UUID NOT NULL,
   user_id UUID NOT NULL,
   message TEXT NOT NULL,
   route TEXT NOT NULL,
   read_at TIMESTAMP WITH TIME ZONE,
   created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
   deleted_at TIMESTAMP WITH TIME ZONE,

   CONSTRAINT notifications_pk PRIMARY KEY (id),
   CONSTRAINT notifications_user_id_fk
       FOREIGN KEY (user_id)
           REFERENCES users(id)
           ON DELETE CASCADE
);
